#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int cur = 0, total = 0;
    for (char &c : line) {
        if (c == ',') {
            total += cur;
            cur = 0;
        }
        else {
            cur = (cur + c) * 17 % 256;
        }
    }

    cout << cur + total << endl;
}
