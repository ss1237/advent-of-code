#include <bits/stdc++.h>
using namespace std;

void load_vals(string line, vector<long long> &arr) {
    line += " ";
    int idx = 0;
    while (idx != line.length()) {
        string num = line.substr(idx, line.find(" ", idx) - idx);
        arr.push_back(stoll(num));
        idx = line.find(" ", idx) + 1;
    }
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<long long> nums;
    line = line.substr(line.find(" ") + 1);
    load_vals(line, nums);

    getline(input, line);
    getline(input, line);

    while (line != "") {
        vector<vector<long long>> transforms;
       
        getline(input, line);
        while (line != "") {
            vector<long long> temp;
            load_vals(line, temp);
            transforms.push_back(temp);
            getline(input, line);
        }

        for (int i = 0; i < nums.size(); i++) {
            for (vector<long long> &cur : transforms) {
                if (nums[i] >= cur[1] && nums[i] < cur[1] + cur[2]) {
                    nums[i] += cur[0] - cur[1];
                    break;
                }
            }
        }

        getline(input, line);
    }

    sort(nums.begin(), nums.end());
    cout << nums[0] << endl;

}
