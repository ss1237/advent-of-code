#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int total = 0;

    while (line != "") {
        int first = -1, last = -1;
        for (int i = 0; i < line.length(); i++) {
            if (line[i] >= '0' && line[i] <= '9') {
                if (first == -1)
                    first = line[i] - '0';
                last = line[i] - '0';
            }
        }
        total += first * 10 + last;
        getline(input, line);
    }

    cout << total << endl;
}
