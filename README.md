# Solution to the assessment
This project contains the solution to an assessment. It is intended to be seen by the reviewer but since it was requested to publish it publicly anybody will be able to see the solution.

1. src/main/java/com/automwrite/assessment/service/impl/LlmServiceImpl.java catches an exception and swallows it returning empty string instead of rethrowing it again so that the controller can return a proper error to the caller.
2. src/main/java/com/automwrite/assessment/service/util/DocumentService.java is missing the implementation for insertContentIntoTemplate which has to use apache poi to read the template docx, locate a placeholder in a paragraph and replace it with the content received by the LLM.
3. src/main/java/com/automwrite/assessment/controller/Controller.java needs try/catch to ensure that exceptions are handled as errors, otherwise it might return 200 instead of a proper error code.
4. Having the key in src/main/resources/application.properties is not recommended but if it must be there then .gitignore should be added to ensure that the key is not committed to the repo.              
5. INFO: By default the generated recommendation.docx is stored locally in the project root which is assumed to be on purpose so that the file gets committed to the repository for review. If this is not the purpose it should be added to .gitignore.
6. INFO: The solution can be run from CLI as follows:
./gradlew build && ./gradlew bootRun
8. INFO: The solution can be tested with curl:
curl -X POST -F "file=@src/main/resources/userintent/userintent.txt" http://localhost:8080/api/user-request

# Automwrite Take-Home Assessment

Please find a copy of the assessment in this folder, along with all relevant input files (`userintent.txt`, `client.json`, `organisation.json`, and the template `.docx`). The exercise is to provide a `.docx` output that is client-presentable.

## Overview
REST API that processes user intent from a `.txt` file, integrates it with data from JSON files, and outputs a formatted `.docx` document using the provided template.

## Requirements
- Java 17
- Apache POI
- Spring Boot
- Claude API key (optional for LLM-based processing)

## Input Files (found in the resources folder)
- `userintent.txt`: Contains the user's intent to be processed.
- `client.json`: Represents information about a client.
- `organisation.json`: Represents information about the organisation and possible outcomes.
- Template `.docx`: A Word document template where the processed user intent will be inserted.

## Task
1. Parse the `userintent.txt` file to extract the user's intent.
2. Parse `client.json` and `organisation.json` into objects.
3. Process the user intent and map it to the client and organisation details.
4. Optionally use the LLM service to refine or transform the text.
5. Insert the processed text into the relevant section of the provided `.docx` template.
6. Save the output as a new `.docx` file.

Feel free to use your own equivalent JSONS or intent .txt files if of equivalent complexity should you find this preferable. 

## API Endpoint

POST /api/user-request Body: multipart/form-data

userIntent (file): The .txt file containing user intent.
template (file): The .docx template file.


## Examples
- **Input 1**:
  - `userintent.txt`: "I want to transfer my pension from Aviva to Fidelity."
  - Output: a `.docx` letter recommending a pension transfer from Aviva to Fidelity. It must contain details from the both the Aviva and Fidelity plans.

- **Input 2**:
  - `userintent.txt`: "I would like to consolidate my pensions into one provider."
  - Output: a `.docx` letter detailing a consolidation of pensions.

## Contact
If you have any questions, please email:
- Logan.Gibson@automwrite.co.uk
