#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define ld long double

vector<ld> zeros(3, 0);

struct Stone {
    ll px, py, pz, vx, vy, vz;
};

vector<ld> intersect(Stone &a, Stone &b) {
    ld d = a.vx * b.vy - a.vy * b.vx;
    if (d == 0) return zeros;
    ld i1 = a.vy * a.px - a.vx * a.py;
    ld i2 = b.vy * b.px - b.vx * b.py;
    ld x = (-b.vx * i1 + a.vx * i2) / d;
    ld y = (-b.vy * i1 + a.vy * i2) / d;
    if ((x - a.px) * a.vx < 0 || (y - a.py) * a.vy < 0) return zeros; 
    if ((x - b.px) * b.vx < 0 || (y - b.py) * b.vy < 0) return zeros;

    d = a.vx * b.vz - a.vz * b.vx;
    if (d == 0) return zeros;
    i1 = a.vz * a.px - a.vx * a.pz;
    i2 = b.vz * b.px - b.vx * b.pz;
    ld x2 = (-b.vx * i1 + a.vx * i2) / d;
    ld z = (-b.vz * i1 + a.vz * i2) / d;
    if ((z - a.pz) * a.vz < 0 || (z - b.pz) * b.vz < 0) return zeros;
    
    if (abs(x - x2) > 1e-4) return zeros;

    return {x, y, z};
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

    ll n = 0;
    while (true) {
        n++;
        cout << n << endl;
        for (ll vx = 0; vx <= n; vx++) {
            for (ll vy = 0; vy <= n - vx; vy++) {
                ll vz = n - vx - vy;
                for (int it1 = 0; it1 < 2; it1++) {
                vx *= -1;
                for (int it2 = 0; it2 < 2; it2++) {
                vy *= -1;
                for (int it3 = 0; it3 < 2; it3++) {
                vz *= -1;
                vector<ld> pt = zeros;
                bool works = true;
                for (int i = 0; i < 6; i++) {
                    Stone a = trajs[i];
                    a.vx -= vx;
                    a.vy -= vy;
                    a.vz -= vz;
                    for (int j = 0; j < i; j++) {
                        Stone b = trajs[j];
                        b.vx -= vx;
                        b.vy -= vy;
                        b.vz -= vz;
                        vector<ld> cur_pt = intersect(a, b);
                        ld v0 = a.vy * (pt[0] - a.px) - a.vx * (pt[1] - a.py);
                        ld v1 = a.vz * (pt[0] - a.px) - a.vx * (pt[2] - a.pz);
                        ld v2 = a.vy * (pt[2] - a.pz) - a.vz * (pt[1] - a.py);
                        ld v3 = b.vy * (pt[0] - b.px) - b.vx * (pt[1] - b.py);
                        ld v4 = b.vz * (pt[0] - b.px) - b.vx * (pt[2] - b.pz);
                        ld v5 = b.vy * (pt[2] - b.pz) - b.vz * (pt[1] - b.py);
                        vector<ld> vals {v0, v1, v2, v3, v4, v5};
                        bool on_both = (vals == vector<ld>(6, 0));

                        if (vals == vector<ld>(6, 0)) {
                            cur_pt = pt;
                        }
                        b.vx += vx;
                        b.vy += vy;
                        b.vz += vz;
                        if (cur_pt == zeros && !on_both) {
                            works = false;
                            break;
                        }
                        if (i + j == 1) {
                            pt = cur_pt;
                        }
                        else if (pt != cur_pt) {
                            works = false;
                            break;
                        }
                    }
                    a.vx += vx;
                    a.vy += vy;
                    a.vz += vz;
                    if (!works) break;
                }

                if (!works) continue;

                cout << setprecision(16) << pt[0] + pt[1] + pt[2] << endl;
                return 0;
                }
                }
                }
            }
        }
    }

}
