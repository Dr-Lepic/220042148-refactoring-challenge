# Code Smells - 220042148

---

## 1. Conditional Complexity
### in the ``SystemFlowRunner`` class. Here we have a large method that has many if/else conditions.

## 2. Large Class
### the ``SystemFlowRunner`` class. It has many methods and is responsible for a lot of different things.

## 3. Data Class
### the ``Person``, ``Buyer``, ``Seller``, ``Vehicle`` and its subclasses. They mostly just hold data and don't have much behavior.

## 4. Duplicate code
### in the ``DataStore`` class. There are several methods that have similar code for saving and loading data. There just the file name is different.

## 5. Primitive Obsession
### in the ``Vehicle`` class, the year is String also the registration number is also String. It should be a date data type.

## 6. Shotgun Surgery
### in the ``DataStore`` class, there are several methods that have same logic and structure. Changing one and lead to change all the code.

## 7. Long Method
### in the ``SystemFlowRunner`` class, the `run()` method is very large and does so many things.

## 8. Magic Numbers
### in the `SystemFlowRunner` class, there are hardcoded numbers that doesn't explain their meaning.

## 9. Divergent Change
### in the `SystemFlowRunner` class, it has many methods that are responsible for different things. If we want to change one thing, we might have to change many methods.

## 10. Lazy Class
### in the `Seller` class, it doesn't have any new behavior other than it's parent `Person` class.

## 11. Dead Code
### in the entire codebase, there are some getter, setter methods that are never used anywhere.



