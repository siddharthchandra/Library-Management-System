n,k=input().split()
n=int(n)
k=int(k)
d1={}
d2={}
n1=0
n2=0
c1=1
c2=1
for i in range(0,n):
    word=input().split()
    if(int(word[0])==1):
        n1+=1
        for i in range(1,len(word)):
            if( word[i] not in d1):
                d1[word[i]]=1
            else:
                d1[word[i]]+=1
    else:
        n2+=1
        for i in range(1,len(word)):
            if( word[i] not in d2):
                d2[word[i]]=1
            else:
                d2[word[i]]+=1
for i in range(0,k):
    word=input().split()
    for i in range(0,len(word)):
        if(word[i] in d1):
            c1=c1*d1[word[i]]
        if(word[i] in d2):
            c2=c2*d2[word[i]]
    c1=c1*n1
    c2=c2*n2
    if(c1>=c2):
        print("1")
    else:
        print("2")
    
