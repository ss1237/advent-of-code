#include <bits/stdc++.h>
using namespace std;

#define MAX 1000000000

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<vector<int>> grid;

    while (line != "") {
        vector<int> tmp;
        for (char &c : line) {
            tmp.push_back(c - '0');
        }
        grid.push_back(tmp);
        getline(input, line);
    }

    deque<vector<int>> q;
    vector<vector<vector<int>>> vis(grid.size(), vector<vector<int>>(grid[0].size(), vector<int>(4, MAX)));
    vector<int> dirs {0, 0, -1, 1};

    q.push_back(vector<int> {0, 0, 4});
    vis[0][0] = vector<int>(4, 0);
    
    while (!q.empty()) {
        vector<int> cur = q.front();
        q.pop_front();

        int r = cur[0], c = cur[1], d = cur[2];

        for (int i = 0; i < 4; i++) {
            if (i / 2 == d / 2) continue;
            int delta = 0;
            for (int j = 1; j <= 3; j++) { 
                int nr = r + j * dirs[i], nc = c + j * dirs[3 - i];
                
                if (nr >= 0 && nc >= 0 && nr < grid.size() && nc < grid[0].size()) {
                    delta += grid[nr][nc];
                    if (vis[nr][nc][i] > vis[r][c][d] + delta) {
                        vis[nr][nc][i] = vis[r][c][d] + delta;
                        q.push_back(vector<int> {nr, nc, i});
                    }
                }
                else break;
            }
        }
    }

    vector<int> tmp = vis[vis.size() - 1][vis[0].size() - 1];
    cout << *min_element(tmp.begin(), tmp.end()) << endl;
}
