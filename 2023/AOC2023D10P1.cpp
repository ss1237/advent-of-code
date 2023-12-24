#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<string> grid;
    int sr = -1, sc = -1;

    while (line != "") {
        if (line.find("S") != line.npos) {
            sr = grid.size();
            sc = line.find("S");
        }
        grid.push_back(line);
        getline(input, line);
    }

    deque<pair<int, int>> q;
    vector<vector<int>> dists(grid.size(), vector<int>(grid[0].length(), -1));
    q.push_back(make_pair(sr, sc));
    dists[sr][sc] = 0;
    int ans = 0;

    vector<int> d {0, 0, 1, -1};
    vector<string> chars {"-J7S", "-LFS", "|7FS", "|LJS"};
    vector<string> chars_adj {"-LF", "-J7", "|LJ", "|7F"};

    while (!q.empty()) {
        pair<int, int> cur = q.front();
        q.pop_front();
        int r = cur.first, c = cur.second;
        ans = max(ans, dists[r][c]);

        for (int i = 0; i < 4; i++) {
            int nr = r + d[i], nc = c + d[3 - i];
            if (nr >= 0 && nc >= 0 && nr < dists.size() && nc < dists[0].size() && chars[i].find(grid[r][c]) != chars[i].npos && chars_adj[i].find(grid[nr][nc]) != chars_adj[i].npos && dists[nr][nc] == -1) {
                q.push_back(make_pair(nr, nc));
                dists[nr][nc] = dists[r][c] + 1;
            }
        }
    }

    cout << ans << endl;
}
