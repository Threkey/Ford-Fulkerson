# Ford-Fulkerson
  Frod-Fulkerson 알고리즘은 그래프에서 두 정점 사이에 얼마나 많은 Flow(유량)을 보낼 수 있는지를 계산하는 Network Flow에 적용되는 알고리즘의 하나이다.
  
## Network Flow
  Ford-Fulkerson 알고리즘을 알기 위해선 먼저 네트워크 플로우에 대해 알아야한다.
  최대유량(Maximum Flow)라고도 불리는 네트워크 플로우(Network Flow)알고리즘은 그래프상에서 한 정점에서 다른 한 정점으로 흐를 수 있는 데이터의 최대 크기를 구하는 알고리즘이다.
  
  1. Capacity : 용량, 간선이 최대로 감당할 수 있는 데이터의 양, c(u,v)
  2. Flow : 유량, 실제로 흐르고있는 데이터의 양, f(u,v)
  3. Source : 시작점
  4. Sink : 도착점
  
  
## Ford-Fulkerson Agorithm
  Ford-Fulkerson 알고리즘은 DFS(깊이 우선 탐색)방식으로의 탐색을 더 이상의 증가경로(Source에서 Sink로 Flow를 보내는 경로)를 찾을 수 없을 때 까지 반복하는 방식으로 이루어지는데, 그 과정에서 '유량의 대칭성'이라는 속성을 적용시키게 된다.
  
  유량의 대칭성 : f(u,v) = -f(v,u), u에서 v로 흘러가는 유량은 v에서 u로 흘러가는 음의 유량과 같다.
  
  예를들면 이런것이다.
  
  ![image](https://user-images.githubusercontent.com/101376843/164888803-daa6a118-fa97-4a13-889e-64346bbb4816.png)
  
  간선의 숫자는 (유량)/(용량)이다.
  
  위와 같은 그래프가 있다고 하면 S에서 T로 보낼 수 있는 최대 유량은 4일것이다.
  
  이를 Ford-Fulkerson 알고리즘을 통해 구하기 위해 DFS를 적용시켜 진행하다 보면 경로를 어떻게 선택했는지에 따라 아래와 같은 상황이 나올 수 있다.
  
  ![image](https://user-images.githubusercontent.com/101376843/164889031-4a775b50-73b1-4e07-92c4-5694a06400b5.png)
  
  이렇게 되면 더 이상의 증가경로를 찾을 수 없게 된다.
  
  이 때 필요한 것이 유량의 대칭성이다.
  A에서 B로 흘러가는 유량 1을 f(A,B) = -f(B,A)라는 속성을 통해 B에서 A로의 역간선을 만들어 -1의 유량을 흘려보내는 것이다.
  쉽게 말해서 왔던 길을 되돌아간다고 생각하면 된다.
  
  그렇게 하면 아래와 같이 최대 유량을 구할 수 있다.
  
  ![image](https://user-images.githubusercontent.com/101376843/164891216-e434f1e2-a0ee-428d-9a99-5c0e5f2624b0.png)
  
  
  Ford-Fulkerson 알고리즘은
  
  1. 모든 간선과 역간선의 유량을 0으로 둔다
  2. Source에서 Sink까지의 잔여용량이 남은 증가경로를 DFS로 찾는다.
  3. 찾은 증가경로에서 가장 용량이 낮은 간선의 용량만큼 유량을 흘려보낸다.
  4. 유량의 대칭성을 통해 흘려보낸 유량의 음수값만큼 역간선에 흘려보낸다
  5. 더 이상 잔여용량이 남은 증가경로가 없을 때 까지 위 과정을 반복한다.
  
  의 순서로 동작하게 된다.
  
  이러한 Ford-Fulkerson 알고리즘의 단점을 보자.
  
  ### 시간복잡도와 단점
  Ford-Fulkerson 알고리즘의 시간복잡도는 O((V+E)F), F는 최대유량(Flow)이다. 최대 유량이 커지면 커질수록 느리게 작동된다는 것이다.<br>
  또한, DFS방식을 사용하는 이 알고리즘은 비효율적으로 동작하는 경우가 있는데 이러한 단점을 보완한 Edmonds-Karp 알고리즘이 있다.
  
  ![image](https://user-images.githubusercontent.com/101376843/164960517-94888f63-3f0c-422e-8339-2dc3663421a0.png)
  
  △최악의 경우 예시

## Edmonds-Karp Algorithm
  DFS방식으로 그래프를 탐색하는 Ford-Fulkerson 알고리즘과 달리 Edmonds-Karp 알고리즘은 BFS(너비 우선 탐색)방식으로 그래프를 탐색하며 증가경로를 찾는다.<br>
  나머지의 과정은 Ford-Fulkerson 알고리즘과 동일하다.
  
  이 알고리즘은 O(VE^2)의 시간 복잡도를 가지게 되는데, Ford-Fulkerson 알고리즘과 달리 유량의 영향을 받지 않는다.<br>
  
## Ford-Fulkerson vs Edmonds-Karp

  Edmonds-Karp 알고리즘은 유량의 영향을 받지 않기때문에 일반적인 상황에서는 Ford-Fulkerson 알고리즘보다 더 빠르고 효율적으로 동작하게 되지만, 간선이 많고 유량이 적은 경우에는 Ford-Fulkerson 알고리즘이 더 효율적으로 동작하므로 상황에 따라 선택해야 한다.
