package com.CK;

public class Main {

    public static void main(String[] args) {
        new Solution().numberOfPatterns(1, 3);
    }
}

class Solution {
    public int numberOfPatterns(int m, int n) {
        Integer[][] dp = new Integer[10][n + 1];
        boolean[] visited = new boolean[10];
        int count = 0;
        for (int max = m; max <= n; max++) {
            for (int st = 1; st < 10; st++) {
                visited[st] = true;
                count += dfs(visited, st, max - 1);
                visited[st] = false;
            }
        }
        return count;
    }

    private int dfs(boolean[] visited, int st, int max) {
        if (max == 0) return 1;
        int ct = 0;
        for (int next = 1; next < 10; next++) {
            if (!isValid(visited, st, next)) continue;
            visited[next] = true;
            ct += dfs(visited, next, max - 1);
            visited[next] = false;
        }
        return ct;
    }

    private boolean isValid(boolean[] visited, int st, int next) {
        if (visited[next]) return false;
        if (st + next == 4 || st + next == 10 || st + next == 16 || st * next == 7 || st * next == 27) {
            return visited[(st + next) / 2];
        }
        return true;
    }
}

 class Solution {
    /**
     * @param m:
     * @param n:
     * @return: the total number of unlock patterns of the Android lock screen
     */
    public int numberOfPatterns(int m, int n) {
        // Write your code here
        int skip[][] = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean vis[] = new boolean[10];
        int rst = 0;
        for(int i = m; i <= n; ++i) {
            rst += DFS(vis, skip, 1, i - 1) * 4;
            rst += DFS(vis, skip, 2, i - 1) * 4;
            rst += DFS(vis, skip, 5, i - 1);
        }
        return rst;
    }
    int DFS(boolean vis[], int[][] skip, int cur, int remain) {
        if(remain < 0){
            return 0;
        }
        if(remain == 0){
            return 1;
        }
        vis[cur] = true;
        int rst = 0;
        for(int i = 1; i <= 9; ++i) {
            if(!vis[i] && (skip[cur][i] == 0 || (vis[skip[cur][i]]))) {
                rst += DFS(vis, skip, i, remain - 1);
            }
        }
        vis[cur] = false;
        return rst;
    }
}