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

void clean_ranges(vector<pair<long long, long long>> &ranges) {
    sort(ranges.begin(), ranges.end());
    for (int i = 0; i < ranges.size() - 1; i++) {
        if (ranges[i].first + ranges[i].second >= ranges[i + 1].first) {
            ranges[i].second = max(ranges[i].first + ranges[i].second, ranges[i + 1].first + ranges[i + 1].second) - ranges[i].first;
            ranges.erase(ranges.begin() + i + 1);
            i--;
        }
    }
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<long long> nums;
    line = line.substr(line.find(" ") + 1);
    load_vals(line, nums);
    vector<pair<long long, long long>> ranges;
    for (int i = 0; i < nums.size(); i += 2) {
        ranges.push_back(make_pair(nums[i], nums[i + 1]));
    }
    clean_ranges(ranges);

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

        vector<pair<long long, long long>> temp_r;

        for (int i = 0; i < ranges.size(); i++) {
            pair<long long, long long> r = ranges[i];
            if (r.second <= 0) continue;
            bool flag = false;
            for (vector<long long> &cur : transforms) {
                if (r.first >= cur[1] + cur[2] || cur[1] >= r.first + r.second) continue;
                
                long long left = max(cur[1], r.first), right = min(cur[1] + cur[2], r.first + r.second);
                temp_r.push_back(make_pair(left + cur[0] - cur[1], right - left));
                if (r.first < left) {
                    ranges.push_back(make_pair(r.first, left - r.first));
                }
                if (r.first + r.second > right) {
                    ranges.push_back(make_pair(right, r.first + r.second - right));
                }
                
                flag = true;
                break;
            }

            if (!flag) {
                temp_r.push_back(r);
            }
        }
        
        ranges = temp_r;
        clean_ranges(ranges);
        getline(input, line);
    }

    cout << ranges[0].first << endl;

}
