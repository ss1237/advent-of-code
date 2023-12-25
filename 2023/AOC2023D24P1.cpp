#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define ld long double
#define MIN 200000000000000
#define MAX 400000000000000

struct Stone {
    ll px, py, pz, vx, vy, vz;
};

bool intersect(Stone &a, Stone &b) {
    ld d = a.vx * b.vy - a.vy * b.vx;
    if (d == 0) return 0;
    ld i1 = a.vy * a.px - a.vx * a.py;
    ld i2 = b.vy * b.px - b.vx * b.py;
    ld x = (-b.vx * i1 + a.vx * i2) / d;
    ld y = (-b.vy * i1 + a.vy * i2) / d;
    if (x < MIN || y < MIN || x > MAX || y > MAX) return 0;
    if ((x - a.px) * a.vx < 0 || (y - a.py) * a.vy < 0) return 0;
    if ((x - b.px) * b.vx < 0 || (y - b.py) * b.vy < 0) return 0;
    return 1;
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<Stone> trajs;

    while (line != "") {
        int prev = -1;
        vector<ll> vals;
        
        string cur = "";
        for (char c : line) {
            if (c == '-' || (c >= '0' && c <= '9')) {
                cur += c;
            }
            else {
                if (cur != "") vals.push_back(stoll(cur));
                cur = "";
            }
        }
        vals.push_back(stoll(cur));

        Stone s;
        s.px = vals[0]; s.py = vals[1]; s.pz = vals[2];
        s.vx = vals[3]; s.vy = vals[4]; s.vz = vals[5];
        trajs.push_back(s);
        getline(input, line);
    }

    int ans = 0;
    for (int i = 0; i < trajs.size(); i++) {
        Stone a = trajs[i];
        for (int j = 0; j < i; j++) {
            Stone b = trajs[j];
            ans += intersect(a, b);
        }
    }

    cout << ans << endl;
}
