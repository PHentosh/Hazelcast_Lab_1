# Hazelcast_Lab_1

# Сконфігуровані 3 ноди і об'єднані в 1 кластер
![image](https://user-images.githubusercontent.com/71725222/235332717-b56c4fbb-396a-431b-8806-d5ceab66e990.png)

# Distributed Map
В мапу записано 1000 значень які розкинуті по 3 нодам
![image_2023-04-06_18-44-00](https://user-images.githubusercontent.com/71725222/235332742-3ee51910-57b5-47b5-84e3-5510b64e409e.png)

Раптово відключена одна нода
![image_2023-04-06_18-44-58](https://user-images.githubusercontent.com/71725222/235332772-598609d3-24c2-474c-b783-eaffaee5c31f.png)

Раптово відключена друга нода
![image_2023-04-06_18-45-28](https://user-images.githubusercontent.com/71725222/235332783-31f4849f-a8d0-405d-a4d8-f66809e88361.png)
Втрати даних не спостерігається


# Distributed Map with locks

В 3 потоках виконуються приклади з 
https://docs.hazelcast.com/imdg/latest/data-structures/map.html#locking-maps
![image_2023-04-25_01-24-26](https://user-images.githubusercontent.com/71725222/235332828-8e64c613-26f5-4363-85b8-75ea39cd212a.png)


# Bounded queue
Налаштована черга з обмеженням у 100 записів

З 2 нод йде читання і з 1 запис 150 значень у чергу <br>
Бачимо зо вичитування відбувається моментально і значення отримує той хто швидше прочитав
![image_2023-04-30_05-12-56](https://user-images.githubusercontent.com/71725222/235332919-b66cd40b-f039-4c7d-be78-08abf8ef936b.png)

Коди йде лише запис без читання більше 100 значень в чергу не влізає, через обмеження, ми просто чекаємо
![image](https://user-images.githubusercontent.com/71725222/235332978-8d7a2b0c-05b7-4806-9c7d-b55fa6cba230.png)




