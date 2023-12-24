#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<string> grid;

    while (line != "") {
        grid.push_back(line);
        getline(input, line);
    }

    map<vector<string>, int> grid2idx;
    map<int, vector<string>> idx2grid;
    int MAX_ITERS = 1000000000, runs = 0, cycle_len = 0;

    for (; runs < MAX_ITERS; runs++) { 
        if (grid2idx.count(grid)) {
            int tmp_idx = grid2idx[grid];
            cycle_len = runs - tmp_idx;
            break;
        }
        grid2idx[grid] = runs;
        idx2grid[runs] = grid;

        for (int c = 0; c < grid[0].length(); c++) {
            for (int r = 0; r < grid.size(); r++) {
                if (r > 0 && grid[r][c] == 'O' && grid[r - 1][c] == '.') {
                    swap(grid[r][c], grid[r - 1][c]);
                    r -= 2;
                }
            }
        }

        for (int r = 0; r < grid.size(); r++) {
            for (int c = 0; c < grid[0].length(); c++) {
                if (c > 0 && grid[r][c] == 'O' && grid[r][c - 1] == '.') {
                    swap(grid[r][c], grid[r][c - 1]);
                    c -= 2;
                }
            }
        }

        for (int c = 0; c < grid[0].length(); c++) {
            for (int r = grid.size() - 1; r >= 0; r--) {
                if (r < grid.size() - 1 && grid[r][c] == 'O' && grid[r + 1][c] == '.') {
                    swap(grid[r][c], grid[r + 1][c]);
                    r += 2;
                }
            }
        }

        for (int r = 0; r < grid.size(); r++) {
            for (int c = grid[0].length() - 1; c >= 0; c--) {
                if (c < grid[0].length() - 1 && grid[r][c] == 'O' && grid[r][c + 1] == '.') {
                    swap(grid[r][c], grid[r][c + 1]);
                    c += 2;
                }
            }
        }
    }

    int start = runs - cycle_len;
    grid = idx2grid[start + (MAX_ITERS - start) % cycle_len];
 
    int ans = 0;
    for (int r = 0; r < grid.size(); r++) {
        for (int c = 0; c < grid[0].length(); c++) {
            if (grid[r][c] == 'O') ans += grid.size() - r;
        }
    }

    cout << ans << endl;
}
