#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    map<char, char> to_val;
    for (char i = '2'; i <= '9'; i++) {
        to_val[i] = i;
    }
    to_val['T'] = (char) ('2' + 8);
    to_val['J'] = (char) ('2' + 9);
    to_val['Q'] = (char) ('2' + 10);
    to_val['K'] = (char) ('2' + 11);
    to_val['A'] = (char) ('2' + 12);

    vector<pair<string, int>> hands;

    while (line != "") {
        int tmp = line.find(" ");
        string hand = "";
        vector<int> freq(13, 0);
        for (char c : line.substr(0, 5)) {
            hand += to_val[c];
            freq[to_val[c] - '2']++;
        }

        vector<pair<int, int>> freqsort;
        for (int i = 0; i < 13; i++) {
            if (freq[i] > 0) freqsort.push_back(make_pair(freq[i], i));
        }

        string ret = "";
        sort(freqsort.rbegin(), freqsort.rend());
        for (auto p : freqsort) {
            ret += (char) (p.first + '0');
        }
        ret += hand;

        int val = stoi(line.substr(tmp + 1));
        hands.push_back(make_pair(ret, val));
        getline(input, line);
    }

    int rv = 0;
    sort(hands.begin(), hands.end());
    for (int i = 0; i < hands.size(); i++) {
        rv += (i + 1) * hands[i].second;
        if (i > 0 && hands[i].first == hands[i - 1].first) cout << hands[i].first << endl;
    }

    cout << rv << endl;
}
