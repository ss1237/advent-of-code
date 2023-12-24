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

    vector<int> row_ct(grid.size(), 0);
    vector<int> col_ct(grid[0].length(), 0);

    for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid[0].length(); j++) {
            if (grid[i][j] == '#') {
                row_ct[i]++;
                col_ct[j]++;
            }
        }
    }

    vector<pair<int, int>> points;
    int cr = 0;
    for (int i = 0; i < grid.size(); i++) {
        int cc = 0;
        for (int j = 0; j < grid[0].length(); j++) {
            if (grid[i][j] == '#') points.push_back(make_pair(cr, cc));
            if (col_ct[j] == 0) cc++;
            cc++;
        }
        if (row_ct[i] == 0) cr++;
        cr++;
    }

    int ans = 0;
    for (auto &a : points) {
        for (auto &b : points) {
            ans += abs(a.first - b.first) + abs(a.second - b.second);
        }
    }

    cout << ans / 2 << endl;
}
