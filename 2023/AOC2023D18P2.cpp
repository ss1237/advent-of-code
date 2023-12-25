#include <bits/stdc++.h>
using namespace std;

#define ll long long

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    ll x = 0, y = 0, boundary = 0;
    vector<pair<ll, ll>> pts;
    pts.push_back(make_pair(x, y));

    while (line != "") {
        int tmp = line.find("#");
        line = line.substr(tmp + 1, line.length() - tmp - 2);
        ll len = stoll(line.substr(0, 5), nullptr, 16);

        if (line[5] == '3') y += len;
        if (line[5] == '1') y -= len;
        if (line[5] == '0') x += len;
        if (line[5] == '2') x -= len;
        

        pts.push_back(make_pair(x, y));
        boundary += len;
        getline(input, line);
    }

    ll area = 0;
    for (int i = 0; i < pts.size() - 1; i++) {
        area += pts[i].first * pts[i + 1].second - pts[i].second * pts[i + 1].first;
    }
    area = abs(area / 2);

    ll interior = area - boundary / 2 + 1;
    ll ans = interior + boundary;
    cout << ans << endl;
}
