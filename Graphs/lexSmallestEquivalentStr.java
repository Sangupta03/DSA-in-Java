package Graphs;

class lexSmallestEquivalentStr { 
    public int[] parent = new int[26]; 
    
    public String smallestEquivalentString(String s1, String s2, String baseStr) { 
        for(int i = 0; i < 26; i++) { 
            parent[i] = i; 
        } 
        
        int n = s1.length(); 
        for(int i = 0; i < n; i++) { 
            int u = s1.charAt(i) - 'a'; 
            int v = s2.charAt(i) - 'a'; 
            union(u, v);  //form grps
        } 
        
        StringBuilder ans = new StringBuilder(); 
        for(char ch : baseStr.toCharArray()) { 
            int par = findParent(ch - 'a');  //root is lexigraphically smallest one in each grp
            ans.append((char)(par + 'a')); 
        } 
        return ans.toString(); 
    } 
    
    int findParent(int node) { 
        if(node == parent[node]) return node; 
        return parent[node] = findParent(parent[node]); 
    } 
    
    void union(int u, int v) { 
        int ulp_u = findParent(u); 
        int ulp_v = findParent(v); 
        if(ulp_u == ulp_v) return; 
        
        if(ulp_u < ulp_v) {  //smaller alphabet as parent
            parent[ulp_v] = ulp_u; 
        } else { 
            parent[ulp_u] = ulp_v; 
        } 
    } 
}
