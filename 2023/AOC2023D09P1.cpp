#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    long long ans = 0;

    while (line != "") {
        vector<vector<long long>> pyramid;
        line += " ";
        int idx = 0;

        vector<long long> nums;
        while (idx != line.length()) {
            int len = line.find(" ", idx) - idx;
            nums.push_back(stoll(line.substr(idx, len)));
            idx = line.find(" ", idx) + 1;
        }

        long long cur = nums.back();

        pyramid.push_back(nums);
        for (int i = nums.size() - 1; i > 0; i--) {
            pyramid.push_back(vector<long long>(i, 0));
            for (int j = 0; j < i; j++) {
                pyramid[nums.size() - i][j] = pyramid[nums.size() - i - 1][j + 1] - pyramid[nums.size() - i - 1][j];
            }
            cur += pyramid[nums.size() - i].back();
        }

        ans += cur;
        getline(input, line);
    }

    cout << ans << endl;

}
