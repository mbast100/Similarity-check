# Similarity check 
 
 This project provides a percentage, indicating the resemblance between 2 texts files.

 Using n grams, where each gram is stored alphabetically, allowing the use of  binary search tree, making the search much more efficient.

 
## Gauging similarity

### Jaccard

1. Count the number of members which are shared between both sets.
2. Count the total number of members in both sets (shared and un-shared).
3. Divide the number of shared members (1) by the total number of members (2).
4. Multiply the number you found in (3) by 100.


### Cosine


## test directory 

Contains the 2 documents to be tested.

## RUN

- Clone the repo
- Put the documents you want to compare in the test directory.
- Open a Terminal & CD in project directory
- run : `java Pastiche test TreeWordMap Jaccard` to test with Jaccard index
- run : `java Pastiche test TreeWordMap Cosine` to test with Cosine similarity 

## Client directory

Contains React front-end application.
This project is intended to become a web application where users can drop their documents and compute a similarity index right away!




