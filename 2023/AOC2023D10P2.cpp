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
    int boundary = 0;

    vector<int> d {0, 0, 1, -1};
    vector<string> chars {"-J7S", "-LFS", "|7FS", "|LJS"};
    vector<string> chars_adj {"-LF", "-J7", "|LJ", "|7F"};
    string types = "|-LJ7F";
    vector<int> maybe_s(6, 0);

    while (!q.empty()) {
        pair<int, int> cur = q.front();
        q.pop_front();
        int r = cur.first, c = cur.second;
        boundary++;

        for (int i = 0; i < 4; i++) {
            int nr = r + d[i], nc = c + d[3 - i];
            if (nr >= 0 && nc >= 0 && nr < dists.size() && nc < dists[0].size() && chars[i].find(grid[r][c]) != chars[i].npos && chars_adj[i].find(grid[nr][nc]) != chars_adj[i].npos && dists[nr][nc] == -1) {
                q.push_back(make_pair(nr, nc));
                dists[nr][nc] = dists[r][c] + 1;
                if (grid[r][c] == 'S') {
                    for (char ch : chars[i].substr(0, 3)) {
                        maybe_s[types.find(ch)] += 1; 
                    }
                }
            }
        }
    }

    for (int i = 0; i < 6; i++) {
        if (maybe_s[i] == 2) {
            grid[sr][sc] = types[i];
        }
    }

    for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid[0].length(); j++) {
            if (dists[i][j] == -1) grid[i][j] = '.';
        }
    }

    set<pair<int, int>> outside;
    for (int i = 0; i < grid.size(); i++) {
        bool within = false;
        int up = -1;
        for (int j = 0; j < grid[i].length(); j++) {
            char ch = grid[i][j];
            if (ch == '|') within = !within;
            if (ch == 'L') up = 1;
            if (ch == 'F') up = 0;
            if (ch == '7') {
                if (up == 1) within = !within;
                up = -1;
            }
            if (ch == 'J') {
                if (up == 0) within = !within;
                up = -1;
            }
            if (!within) {
                outside.insert(make_pair(i, j));
            }
        }
    }

    for (int i = 0; i < grid[0].length(); i++) {
        bool within = false;
        int left = -1;
        for (int j = 0; j < grid.size(); j++) {
            char ch = grid[j][i];
            if (ch == '-') within = !within;
            if (ch == '7') left = 1;
            if (ch == 'F') left = 0;
            if (ch == 'L') {
                if (left == 1) within = !within;
                left = -1;
            }
            if (ch == 'J') {
                if (left == 0) within = !within;
                left = -1;
            }
            if (!within) {
                outside.insert(make_pair(j, i));
            }
        }
    }

    int ans = 0;

    for (int i = 0; i < grid.size(); i++) {
        for (int j = 0; j < grid[0].size(); j++) {
            if (!outside.count(make_pair(i, j)) && dists[i][j] == -1) {
                ans++;
            }
        }
    }

    cout << ans << endl;

}
