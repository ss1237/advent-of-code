#include <bits/stdc++.h>
using namespace std;

long long extract_num(string line) {
    line += " ";
    string ret = "";
    for (int i = 0; i < line.length(); i++) {
        if (line[i] >= '0' && line[i] <= '9') {
            ret += line.substr(i, line.find(" ", i) - i);
            i = line.find(" ", i);
        }
    }
    return stoll(ret);
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    long long time = extract_num(line);
    getline(input, line);
    long long distance = extract_num(line);

    long long ans = 0;
    for (long long j = 1; j < time; j++) {
        if (j + (distance + j - 1) / j <= time) {
            ans++;
        }
    }

    cout << ans << endl;
}
