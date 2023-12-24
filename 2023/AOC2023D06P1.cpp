#include <bits/stdc++.h>
using namespace std;

void extract_nums(string line, vector<int> &arr) {
    line += " ";
    for (int i = 0; i < line.length(); i++) {
        if (line[i] >= '0' && line[i] <= '9') {
            int num = stoi(line.substr(i, line.find(" ", i)));
            arr.push_back(num);
            i = line.find(" ", i);
        }
    }
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<int> times, distances;
    extract_nums(line, times);
    getline(input, line);
    extract_nums(line, distances);

    int ans = 1;

    for (int i = 0; i < times.size(); i++) {
        int cur = 0;
        for (int j = 1; j < times[i]; j++) {
            if (j + (distances[i] + j - 1) / j <= times[i]) {
                cur++;
            }
        }
        ans *= cur;
    }

    cout << ans << endl;
}
