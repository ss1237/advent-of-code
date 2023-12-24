#include <bits/stdc++.h>
using namespace std;

#pragma GCC optimize("O2")

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);
    string moves = line;
    getline(input, line);
    getline(input, line);

    map<string, pair<string, string>> route;
    vector<string> starts;

    while (line != "") {
        string a = line.substr(0, 3);
        string b = line.substr(line.find("(") + 1, 3);
        string c = line.substr(line.find(", ") + 2, 3);

        route[a] = make_pair(b, c);
        if (a[2] == 'A') starts.push_back(a);
        getline(input, line);
    }

    vector<long long> nums;

    for (string &cur : starts) {
        long long ct = 0;
        while (cur[2] != 'Z') {
            if (moves[ct % moves.length()] == 'L') {
                cur = route[cur].first;
            }
            else {
                cur = route[cur].second;
            }
        
            ct++;
        }
        nums.push_back(ct);
    }

    long long rv = 1;

    for (long long &x : nums) {
        rv *= x / __gcd(rv, x);
    }

    cout << rv << endl;

}
