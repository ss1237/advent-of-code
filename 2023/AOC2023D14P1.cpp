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



    for (int c = 0; c < grid[0].length(); c++) {
        for (int r = 1; r < grid.size(); r++) {
            if (r > 0 && grid[r][c] == 'O' && grid[r - 1][c] == '.') {
                swap(grid[r][c], grid[r - 1][c]);
                r -= 2;
            }
        }
    }

    int ans = 0;
    for (int r = 0; r < grid.size(); r++) {
        for (int c = 0; c < grid[0].length(); c++) {
            if (grid[r][c] == 'O') ans += grid.size() - r;
        }
    }

    cout << ans << endl;
}
