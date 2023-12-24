#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int ans = 0;

    while (line != "") {
        vector<string> grid;
        while (line != "") {
            grid.push_back(line);
            getline(input, line);
        }

        for (int i = 0; i < grid.size() - 1; i++) {
            int diffs = 0;
            for (int j = i + 1; j < grid.size(); j++) {
                int tmp_idx = 2 * i - j + 1;
                if (tmp_idx < 0) break;
                for (int k = 0; k < grid[j].length(); k++) {
                    if (grid[j][k] != grid[tmp_idx][k]) diffs++;
                }
            }
            if (diffs == 1) ans += (i + 1) * 100;
        }

        vector<string> gridt(grid[0].length(), string(grid.size(), '.'));
        for (int i = 0; i < grid[0].length(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                gridt[i][j] = grid[j][i];
            }
        }

        for (int i = 0; i < gridt.size() - 1; i++) {
            int diffs = 0;
            for (int j = i + 1; j < gridt.size(); j++) {
                int tmp_idx = 2 * i - j + 1;
                if (tmp_idx < 0) break;
                for (int k = 0; k < gridt[j].length(); k++) {
                    if (gridt[j][k] != gridt[tmp_idx][k]) diffs++;
                }
            }
            if (diffs == 1) ans += i + 1;
        }
        
        getline(input, line);
    }

    cout << ans << endl;

}
