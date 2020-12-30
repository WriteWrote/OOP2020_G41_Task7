# OOP2020_G41_Task7
Supermarket

Leaving the idea of using classes descriptioning goods. Better idea: get Enum with string of products, then create two classes like "Description".
Transmit there all characteristics for countable and uncountable goods. Maybe make several enums, and for any create method like "get expiration days".
That would be nice.
When creating new batch of productType, all that needed are just:
    1. randomly generated name from enum
    2. new Description
    3. randomly generated quantity.
   
I will need an Interface Description, two classes: Countable&Uncountable. Also I will need several enums. I guess for beginning I will stay with one.
Also I need to make a method in enum, which returns expiration days. I guess this is the weak point, but one weak point is better than few weak points.

Talking about Stock and ShoppingRoom. The difference is the Queue of customers, also the quantities of products.
Stock: Map/Set <enum, description>;
ShoppingRoom: Set<enum, description>; Queue <Customers>;
    
NEW: finished with the original project. Added Maven, now managing Gson serializing.\
ToDo: multithreading for pretty output (yeah, just like that)
