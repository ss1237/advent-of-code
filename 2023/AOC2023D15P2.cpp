#include <bits/stdc++.h>
using namespace std;

pair<string, int> extract_pair(string &s) {
    int tmp_idx = s.find("=");
    string name = s.substr(0, tmp_idx);
    int val = stoi(s.substr(tmp_idx + 1));
    return make_pair(name, val);
}

int compute_hash(string &s) {
    int ret = 0;
    for (char &c : s) {
        ret = (ret + c) * 17 % 256;
    }
    return ret;
}

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    vector<vector<pair<string, int>>> boxes(256);
    int idx = 0;
    line += ',';

    while (idx != line.length()) {
        string s = line.substr(idx, line.find(",", idx) - idx);
        if (s.find("=") != s.npos) {
            pair<string, int> p = extract_pair(s);
            int hash = compute_hash(p.first);
            bool found = false;

            for (auto &q : boxes[hash]) {
                if (q.first == p.first) {
                    q.second = p.second;
                    found = true;
                }
            }

            if (!found) {
                boxes[hash].push_back(p);
            }
        }
        else {
            string name = s.substr(0, s.length() - 1);
            int hash = compute_hash(name);
            for (int i = 0; i < boxes[hash].size(); i++) {
                if (boxes[hash][i].first == name) {
                    boxes[hash].erase(boxes[hash].begin() + i);
                    break;
                }
            }
        }
        idx = line.find(",", idx) + 1;
    }
    

    int total = 0;
    for (int i = 0; i < 256; i++) {
        for (int j = 0; j < boxes[i].size(); j++) {
            total += (i + 1) * (j + 1) * boxes[i][j].second;
        }
    }

    cout << total << endl;
}
