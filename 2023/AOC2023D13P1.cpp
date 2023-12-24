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
            bool works = true;
            for (int j = i + 1; j < grid.size(); j++) {
                int tmp_idx = 2 * i - j + 1;
                if (tmp_idx < 0) break;
                if (grid[j] != grid[tmp_idx]) works = false;
            }
            if (works) ans += (i + 1) * 100;
        }

        vector<string> gridt(grid[0].length(), string(grid.size(), '.'));
        for (int i = 0; i < grid[0].length(); i++) {
            for (int j = 0; j < grid.size(); j++) {
                gridt[i][j] = grid[j][i];
            }
        }

        for (int i = 0; i < gridt.size() - 1; i++) {
            bool works = true;
            for (int j = i + 1; j < gridt.size(); j++) {
                int tmp_idx = 2 * i - j + 1;
                if (tmp_idx < 0) break;
                if (gridt[j] != gridt[tmp_idx]) works = false;
            }
            if (works) ans += i + 1;
        }
        
        getline(input, line);
    }

    cout << ans << endl;

}
