#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);
    string moves = line;
    getline(input, line);
    getline(input, line);

    map<string, pair<string, string>> route;

    while (line != "") {
        string a = line.substr(0, 3);
        string b = line.substr(line.find("(") + 1, 3);
        string c = line.substr(line.find(", ") + 2, 3);

        route[a] = make_pair(b, c);
        getline(input, line);
    }

    string cur = "AAA";
    int ct = 0;
    while (cur != "ZZZ") {
        if (moves[ct % moves.length()] == 'L') {
            cur = route[cur].first;
        }
        else {
            cur = route[cur].second;
        }
        ct++;
    }

    cout << ct << endl;

}
