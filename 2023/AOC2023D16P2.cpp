#include <bits/stdc++.h>
using namespace std;

int dirs[4] {0, -1, 0, 1};

bool valid(int &r, int &c, int &d, vector<vector<vector<bool>>> &vis) {
    return r >= 0 && c >= 0 && r < vis.size() && c < vis[0].size() && !vis[r][c][d];
}

int test_case(int sr, int sc, int sd, vector<string> &grid) {
    vector<vector<vector<bool>>> vis(grid.size(), vector<vector<bool>>(grid[0].length(), vector<bool>(4, false)));

    deque<pair<int, int>> posq;
    deque<int> dq;
    vis[sr][sc][sd] = true;
    posq.push_back(make_pair(sr, sc));
    dq.push_back(sd);

    while (!posq.empty()) {
        pair<int, int> pos = posq.front();
        posq.pop_front();
        int d = dq.front();
        dq.pop_front();
        int r = pos.first, c = pos.second;

        if (grid[r][c] == '\\') d ^= 3;
        if (grid[r][c] == '/') d ^= 1; 

        int nr = r + dirs[d], nc = c + dirs[3 - d];
        if ((grid[r][c] == '|' && d % 2 == 0) || (grid[r][c] == '-' && d % 2 == 1)) {
            int bit = 1 - d % 2;
            for (int i = 0; i < 2; i++) {
                d = bit + 2 * i;
                nr = r + dirs[d], nc = c + dirs[3 - d];
                if (valid(nr, nc, d, vis)) {
                    posq.push_back(make_pair(nr, nc));
                    dq.push_back(d);
                    vis[nr][nc][d] = true;
                }
            }
        }
        else if (valid(nr, nc, d, vis)) {
            posq.push_back(make_pair(nr, nc));
            dq.push_back(d);
            vis[nr][nc][d] = true;
        }
    }

    int ans = 0;
    for (int i = 0; i < vis.size(); i++) {
        for (int j = 0; j < vis[0].size(); j++) {
            for (int k = 0; k < 4; k++) {
                if (vis[i][j][k]) {
                    ans++;
                    break;
                }
            }
        }
    }
    
    return ans;
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<string> grid;

    while (line != "") {
        grid.push_back(line);
        getline(input, line);
    }

    int ans = 0;
    for (int i = 0; i < grid.size(); i++) {
        ans = max(ans, max(test_case(i, 0, 0, grid), test_case(i, grid[0].length() - 1, 2, grid)));
    }
    for (int i = 0; i < grid[0].length(); i++) {
        ans = max(ans, max(test_case(0, i, 3, grid), test_case(grid.size() - 1, i, 1, grid)));
    }

    cout << ans << endl;
}
