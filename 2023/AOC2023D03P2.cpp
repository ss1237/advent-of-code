#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<string> lines;

    while (line != "") {
        lines.push_back(line);
        getline(input, line);
    }

    int cur_num = 0, num_digits = 0, total = 0;
    vector<vector<int>> coords, gears;
    bool valid = false;
    for (int i = 0; i < lines.size(); i++) {
        for (int j = 0; j < lines[i].length(); j++) {
            if (lines[i][j] >= '0' && lines[i][j] <= '9') {
                cur_num = cur_num * 10 + (lines[i][j] - '0');
                num_digits++;
                for (int dr = -1; dr <= 1; dr++) {
                    if (i + dr < 0 || i + dr >= lines.size()) continue;
                    for (int dc = -1; dc <= 1; dc++) {
                        if (j + dc < 0 || j + dc >= lines[i].length()) continue;
                        if (lines[i + dr][j + dc] == '*') {
                            valid = true;
                        }
                    }
                }
            }
            else {
                if (lines[i][j] == '*') {
                    vector<int> temp = {i, j};
                    gears.push_back(temp);
                }
                if (valid) {
                    int cur_pos = i * lines[i].length() + j - 1;
                    int row = cur_pos / lines[i].length(), col = cur_pos % lines[i].length();
                    vector<int> temp = {cur_num, row, col - num_digits + 1, col};
                    coords.push_back(temp); 
                }
                num_digits = 0;
                valid = false;
                cur_num = 0;
            }
        }
    }
    
    if (valid) {
        vector<int> temp = {cur_num, (int) lines.size() - 1, (int) lines[0].length() - num_digits, (int) lines[0].length() - 1};
        coords.push_back(temp);
    } 

    for (int k = 0; k < gears.size(); k++) {
        int ct = 0, ans = 1;
        for (int i = 0; i < coords.size(); i++) {
            bool vertical = abs(coords[i][1] - gears[k][0]) <= 1;
            bool horizontal = min(abs(coords[i][2] - gears[k][1]), abs(coords[i][3] - gears[k][1])) <= 1; 
            if (vertical && horizontal) {
                ans *= coords[i][0];
                ct++;
            }
        }
        if (ct == 2) total += ans;
    }
    
    cout << total << endl;

}
