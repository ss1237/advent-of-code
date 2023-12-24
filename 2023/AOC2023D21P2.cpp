#include <bits/stdc++.h>
using namespace std;

#define ll long long

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

    grid[sr][sc] = '.';

    int scale = 3;
    vector<string> biggrid;
    for (int i = 0; i < grid.size(); i++) {
        biggrid.push_back(grid[i]);
        for(int j = 0; j < 2 * scale; j++) {
            biggrid[i] += grid[i];
        }
    }
    for (int i = 0; i < 2 * scale; i++) {
        for (int j = 0; j < grid.size(); j++) {
            biggrid.push_back(biggrid[j]);
        }
    }
    sr += scale * grid.size();
    sc += scale * grid[0].length();
    grid = biggrid;

    vector<vector<ll>> vis(grid.size(), vector<ll>(grid[0].length(), 0));
    vis[sr][sc] = 1;
    vector<int> d {0, 0, -1, 1};
    int NUM_ITERS = 327;

    vector<ll> vals;

    for (int i = 1; i <= NUM_ITERS; i++) {
        vector<vector<ll>> prev = vis;
        vis = vector<vector<ll>>(grid.size(), vector<ll>(grid[0].length(), 0));

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

        if (i % 131 == 65) {
            ll ans = 0;
            for (int r = 0; r < grid.size(); r++) {
                for (int c = 0; c < grid[0].length(); c++) {
                    ans += vis[r][c];
                }
            }
            vals.push_back(ans);
        }
    }

    ll a = (vals[2] - 2 * vals[1] + vals[0]) / 2;
    ll b = vals[1] - vals[0] - a;
    ll c = vals[0];
    ll n = (26501365 - 65) / 131;
    cout << setprecision(16) << a * pow(n, 2) + b * n + c << endl;
}
