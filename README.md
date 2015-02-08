# RecyclerFragment
RecyclerFragment is a Simple to use Android class that is kind of like ListFragment and ListActivity


## CommonMethods
```java

public RecyclerView getRecyclerView()
public void setRecyclerAdapter(RecyclerView.Adapter adapter) 
public void setLayoutManger(RecyclerView.LayoutManager layout)
public void setEmptyView(int layout)
```
# Simple to use
just extend RecyclerFragment 
add at least setRecyclerAdapter(RecyclerView.Adapter adapter) and you are good to go.
Layout manager is setTo LinearLayout by default.
Emptyview not set init.


other methods 
```java
public void setItemAnimator(RecyclerView.ItemAnimator animation)
public void setEmptyViewAnimations(int enterAnimation, int exitAnimation)
```

some basic item animators have been added in these Animators were taken from 
Gabriele Mariotti (gabri.mariotti@gmail.com)
[RecyclerViewItemAnimators](https://github.com/gabrielemariotti/RecyclerViewItemAnimators)    

LICENSE

Copyright 2015 Aaron Fleshner

"THE BEER-WARE LICENSE" (Revision 42): phk@FreeBSD.ORG wrote this file. As long as you retain this notice you can do whatever you want with this stuff. If we meet some day, and you think this stuff is worth it, you can buy me a beer in return Poul-Henning Kamp
