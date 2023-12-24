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

    int cur_num = 0, total = 0;
    bool valid = false;
    for (int i = 0; i < lines.size(); i++) {
        for (int j = 0; j < lines[i].length(); j++) {
            if (lines[i][j] >= '0' && lines[i][j] <= '9') {
                cur_num = cur_num * 10 + (lines[i][j] - '0');
                for (int dr = -1; dr <= 1; dr++) {
                    if (i + dr < 0 || i + dr >= lines.size()) continue;
                    for (int dc = -1; dc <= 1; dc++) {
                        if (j + dc < 0 || j + dc >= lines[i].length()) continue;
                        if (!(lines[i + dr][j + dc] >= '0' && lines[i + dr][j + dc] <= '9') && lines[i + dr][j + dc] != '.') {
                            valid = true;
                        }
                    }
                }
            }
            else {
                if (valid) {
                    total += cur_num; 
                }
                valid = false;
                cur_num = 0;
            }
        }
    }
    
    if (valid) total += cur_num;

    cout << total << endl;

}
