#include <bits/stdc++.h>
using namespace std;

struct condition {
    int var, val;
    bool less;
    string to;
};

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

    getline(input, line);
    int ans = 0;

    while (line != "") {
        int idx = line.find('=');
        vector<int> part;
        for (int i = 0; i < 4; i++) {
            part.push_back(stoi(line.substr(idx + 1, line.find(',', idx) - idx - 1)));
            idx = line.find('=', idx + 1);
        }

        string cur_name = "in";
        while (cur_name != "A" && cur_name != "R") {
            vector<condition> cur_flow = flows[stoid[cur_name]];
            for (condition &cond : cur_flow) {
                int val = part[cond.var];
                if (cond.less ? (val < cond.val) : (val > cond.val)) {
                    cur_name = cond.to;
                    break;
                }
            }
        }

        if (cur_name == "A") {
            for (int &x : part) ans += x;
        }
        getline(input, line);
    }

    cout << ans << endl;
}
