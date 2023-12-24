#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<string> grid;
    int sr, sc;

    while (line != "") {
        grid.push_back(line);
        if (line.find("S") != line.npos) {
            sr = grid.size() - 1;
            sc = line.find("S");
        }
        getline(input, line);
    }

    vector<vector<bool>> vis(grid.size(), vector<bool>(grid[0].length(), false));
    vis[sr][sc] = true;
    grid[sr][sc] = '.';
    vector<int> d {0, 0, -1, 1};
    int NUM_ITERS = 64;

    for (int i = 0; i < NUM_ITERS; i++) {
        vector<vector<bool>> prev = vis;
        vis = vector<vector<bool>>(grid.size(), vector<bool>(grid[0].length(), false));

        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid[0].length(); c++) {
                if (prev[r][c]) {
                    for (int j = 0; j < 4; j++) {
                        int nr = r + d[j], nc = c + d[3 - j];
                        if (nr >= 0 && nc >= 0 && nr < grid.size() && nc < grid[0].length() && grid[nr][nc] == '.') {
                            vis[nr][nc] = true;
                        }
                    }
                }
            }
        }

    }

    int ans = 0;
    for (int r = 0; r < grid.size(); r++) {
        for (int c = 0; c < grid[0].length(); c++) {
            ans += vis[r][c];
        }
    }

    cout << ans << endl;

}
