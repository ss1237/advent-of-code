#include <bits/stdc++.h>
using namespace std;

struct condition {
    int var, val;
    bool less;
    string to;
};

struct range {
    vector<long long> bounds;
    string flow_name;
};

void split_range(range &r, condition &c, vector<long long> &sat, vector<long long> &unsat) {
    int idx = c.var;
    long long lo = r.bounds[2 * idx], hi = r.bounds[2 * idx + 1], val = c.val;
    if (c.less) {
        if (hi < val) {
            sat = r.bounds;
        }
        else if (val <= lo) {
            unsat = r.bounds;
        }
        else {
            sat = r.bounds;
            unsat = r.bounds;
            sat[2 * idx + 1] = val - 1;
            unsat[2 * idx] = val;
        }
    }
    else {
        if (lo > val) {
            sat = r.bounds;
        }
        else if (val >= hi) {
            unsat = r.bounds;
        }
        else {
            sat = r.bounds;
            unsat = r.bounds;
            sat[2 * idx] = val + 1;
            unsat[2 * idx + 1] = val;
        }
    }
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    string vartoint = "xmas";
    map<string, int> stoid;
    vector<vector<condition>> flows;
    
    while (line != "") {
        int tmp = line.find('{');
        string name = line.substr(0, tmp);
        line = line.substr(tmp + 1, line.length() - tmp - 2);
        
        int idx = 0;
        vector<condition> conds;
        stoid.insert({name, stoid.size()});

        while (true) {
            condition cur;

            if (line.find(',', idx) == line.npos) {
                cur.var = 0;
                cur.less = false;
                cur.val = 0;
                cur.to = line.substr(idx);
                conds.push_back(cur);
                break;
            }

            cur.var = vartoint.find(line[idx]);
            cur.less = line[idx + 1] == '<';
            tmp = line.find(':', idx);
            cur.val = stoi(line.substr(idx + 2, tmp - idx - 2));
            cur.to = line.substr(tmp + 1, line.find(',', idx) - tmp - 1);

            conds.push_back(cur);

            idx = line.find(',', idx) + 1;
        }

        flows.push_back(conds);
        getline(input, line);
    }

   long long ans = 0;
   deque<range> q;
   range start = {vector<long long> {1, 4000, 1, 4000, 1, 4000, 1, 4000}, "in"};
   q.push_back(start);
   vector<long long> null(8, 0);

   while (!q.empty()) {
        range cur = q.front();
        q.pop_front();

        if (cur.flow_name == "A") {
            long long tmp = 1;
            for (int i = 0; i < 8; i += 2) {
                tmp *= cur.bounds[i + 1] - cur.bounds[i] + 1;
            }
            ans += tmp;
            continue;
        }
        if (cur.flow_name == "R") continue;

        vector<condition> flow = flows[stoid[cur.flow_name]];
        for (condition &cond : flow) {
            vector<long long> sat(8, 0), unsat(8, 0);
            split_range(cur, cond, sat, unsat);

            if (sat != null) {
                range to_push = {sat, cond.to};
                q.push_back(to_push);
            }
            if (unsat != null) {
                cur.bounds = unsat;
            }
            else break;
        }
   }

   cout << ans << endl;

}
