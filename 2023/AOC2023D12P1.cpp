#include <bits/stdc++.h>
using namespace std;

int solve(string &row, int r_idx, vector<int> &lens, int l_idx) {
    if (l_idx == lens.size()) {
        if (row.find('#', r_idx) == row.npos) return 1;
        else return 0;
    }
    if (r_idx == row.length()) {
        return 0;
    }

    int ret = 0;
    if (row[r_idx] == '.' || row[r_idx] == '?') {
        ret += solve(row, r_idx + 1, lens, l_idx);
    }
    if (row[r_idx] == '#' || row[r_idx] == '?') {
        if (row.length() - r_idx < lens[l_idx]) return 0;
        bool works = true;
        for (int i = 0; i < lens[l_idx]; i++) {
            if (row[r_idx] == '.') works = false;
            r_idx++;
        }
        if (works) {
            if (r_idx == row.length() && l_idx + 1 == lens.size()) ret += 1;
            if (r_idx < row.length() && row[r_idx] != '#') {
                ret += solve(row, r_idx + 1, lens, l_idx + 1);
            }
        }
    }

    return ret;
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int ans = 0;

    while (line != "") {
        line += ",";
        int idx = line.find(" ") + 1;
        string row = line.substr(0, idx - 1);
        vector<int> lens;
        
        while (idx != line.length()) {
            lens.push_back(stoi(line.substr(idx, line.find(',', idx) - idx)));
            idx = line.find(',', idx) + 1;
        }
       
        int tmp = solve(row, 0, lens, 0);
        ans += tmp;
        getline(input, line);
    }

    cout << ans << endl;

}
