#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int total = 0, id = 1;
    vector<int> cts(200, 0);

    while (line != "") {
        vector<int> wins, nums;
        int cur = 0;
        int win_idx = line.find(": ") + 2, num_idx = line.find(" | ") + 3;
        int win_len = (num_idx - win_idx - 2) / 3, num_len = (line.length() - num_idx + 1) / 3;
        
        for (int i = 0; i < win_len; i++) {
            string num_str = line.substr(i * 3 + win_idx, 2);
            if (num_str[0] == ' ') num_str = num_str.substr(1, 1);
            wins.push_back(stoi(num_str));
        }

        for (int i = 0; i < num_len; i++) {
            string num_str = line.substr(i * 3 + num_idx, 2);
            if (num_str[0] == ' ') num_str = num_str.substr(1, 1);
            nums.push_back(stoi(num_str));
        }

        for (int i : wins) {
            for (int j : nums) {
                if (i == j) {
                    cur++;
                    break;
                }
            }
        }

        cts[id]++;
        for (int i = 1; i <= cur; i++) {
            cts[id + i] += cts[id];
        }
        
        id++;
        getline(input, line);
    }

    for (int x : cts) total += x;
    cout << total << endl;
}
