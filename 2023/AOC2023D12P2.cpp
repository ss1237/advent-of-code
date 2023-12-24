#include <bits/stdc++.h>
using namespace std;

long long solve(string &row, int r_idx, vector<long long> &lens, int l_idx, vector<vector<long long>> &dp) {
    if (l_idx == lens.size()) {
        if (row.find('#', r_idx) == row.npos) return 1;
        else return 0;
    }
    if (r_idx == row.length()) {
        return 0;
    }

    if (dp[r_idx][l_idx] != -1) return dp[r_idx][l_idx];

    long long ret = 0;
    if (row[r_idx] == '.' || row[r_idx] == '?') {
        ret += solve(row, r_idx + 1, lens, l_idx, dp);
    }
    if (row[r_idx] == '#' || row[r_idx] == '?') {
        if (row.length() - r_idx < lens[l_idx]) return 0;
        bool works = true;
        for (int i = 0; i < lens[l_idx]; i++) {
            if (row[r_idx + i] == '.') works = false;
        }
        if (works) {
            int tmp_idx = r_idx + lens[l_idx];
            if (tmp_idx == row.length() && l_idx + 1 == lens.size()) ret += 1;
            if (tmp_idx < row.length() && row[tmp_idx] != '#') {
                ret += solve(row, tmp_idx + 1, lens, l_idx + 1, dp);
            }
        }
    }

    dp[r_idx][l_idx] = ret;
    return ret;
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    long long ans = 0;

    while (line != "") {
        line += ",";
        int idx = line.find(" ") + 1;
        string row = line.substr(0, idx - 1);
        vector<long long> lens;
        
        while (idx != line.length()) {
            lens.push_back(stoll(line.substr(idx, line.find(',', idx) - idx)));
            idx = line.find(',', idx) + 1;
        }

        string tmp_row = '?' + row;
        int tmp_lens_size = lens.size();
        for (int i = 0; i < 4; i++) {
            row += tmp_row;
            for (int j = 0; j < tmp_lens_size; j++) {
                lens.push_back(lens[j]);
            }
        }

        vector<vector<long long>> dp(row.length(), vector<long long>(lens.size(), -1));
       
        long long tmp = solve(row, 0, lens, 0, dp);
        ans += tmp;
        
        getline(input, line);
    }

    cout << ans << endl;

}
