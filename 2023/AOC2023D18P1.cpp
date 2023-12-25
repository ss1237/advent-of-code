#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int x = 0, y = 0, boundary = 0;
    vector<pair<int, int>> pts;
    pts.push_back(make_pair(x, y));

    while (line != "") {
        int tmp = line.find(" ", 2);
        int len = stoi(line.substr(2, tmp - 2));
        if (line[0] == 'U') y += len;
        if (line[0] == 'D') y -= len;
        if (line[0] == 'R') x += len;
        if (line[0] == 'L') x -= len;

        pts.push_back(make_pair(x, y));
        boundary += len;
        getline(input, line);
    }

    int area = 0;
    for (int i = 0; i < pts.size() - 1; i++) {
        area += pts[i].first * pts[i + 1].second - pts[i].second * pts[i + 1].first;
    }
    area = abs(area / 2);

    int interior = area - boundary / 2 + 1;
    int ans = interior + boundary;
    cout << ans << endl;
}
