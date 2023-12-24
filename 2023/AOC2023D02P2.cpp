#include <bits/stdc++.h>
using namespace std;

int main() {
    ifstream input("input.txt");
    string line;
    getline(input, line);

    int total = 0;

    while (line != "") {
        line += " ";
        int beg = 0, end = line.find(" ");
        int red = 0, blue = 0, green = 0;
        string last_num = "";
        while (end != -1) {
            string cur = line.substr(beg, end - beg);
            if (cur[0] >= 'a' && cur[0] <= 'z') {
                if (cur[0] == 'r') red = max(red, stoi(last_num));
                if (cur[0] == 'g') green = max(green, stoi(last_num));
                if (cur[0] == 'b') blue = max(blue, stoi(last_num));
            }
            else {
                last_num = cur;
            }
            beg = end + 1;
            end = line.find(" ", end + 1);
        }
        total += red * blue * green;
        getline(input, line);
    }
    
    cout << total << endl;
}
