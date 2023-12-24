#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int total = 0;
    string vals[10] = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    int lens[10] = {4, 3, 3, 5, 4, 4, 3, 5, 5, 4};

    while (line != "") {
        int first = -1, last = -1;

        for (int i = 0; i < line.length(); i++) {
            int value = line[i] - '0';
            
            for (int j = 1; j <= 9; j++) {
                if (lens[j] <= i + 1 && vals[j] == line.substr(i - lens[j] + 1, lens[j])) {
                    value = j;
                }
            }
            
            if (value >= 0 && value <= 9) {
                if (first == -1)
                    first = value;
                last = value;
            }
        }
        total += first * 10 + last;
        // cout << first << last << endl;
        getline(input, line);
    }

    cout << total << endl;
}
