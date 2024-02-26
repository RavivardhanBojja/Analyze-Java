package com.capgemini.Analyze.serviceImpl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.capgemini.Analyze.dto.AzureADOBugMasterDTO;
import com.capgemini.Analyze.dto.DefectMasterDTO;
import com.capgemini.Analyze.service.AzureADOService;

@Service
public class AzureDevopsServiceImpl implements AzureADOService{
	
	public List<DefectMasterDTO> getBugInfoAzureADO(AzureADOBugMasterDTO azureADOBugMasterDTO) {
        List<DefectMasterDTO> defectslist = new ArrayList<>();

		String organization = azureADOBugMasterDTO.getOrgName();
        String project = azureADOBugMasterDTO.getProjectName();
        String pat = azureADOBugMasterDTO.getPat();
        Long solutionId = azureADOBugMasterDTO.getSolutionId();
        String apiUrl = String.format(
                "https://dev.azure.com/%s/%s/_apis/wit/wiql?api-version=6.0", organization, project);

        HttpClient client = HttpClient.newHttpClient();

        String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString((":" + pat).getBytes());

        // WIQL (Work Item Query Language) to fetch all bugs in the project
        String query = "{\"query\": \"SELECT [System.Id] FROM WorkItems WHERE [System.WorkItemType] = 'Bug'\"}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", authHeaderValue)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject jsonResponse = new JSONObject(response.body());
                JSONArray workItems = jsonResponse.getJSONArray("workItems");
                for (int i = 0; i < workItems.length(); i++) {
                    JSONObject workItem = workItems.getJSONObject(i);
                    long id = workItem.getInt("id");
//                    System.out.println("Bug ID: " + id);
                    DefectMasterDTO defectMasterDTO = new DefectMasterDTO();
                    defectMasterDTO= fetchBugDetails(id, organization, project, pat,solutionId);
                    defectslist.add(defectMasterDTO);
                }
                    return defectslist;

                
            } else {
                System.err.println("Error fetching work items. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
    public static DefectMasterDTO fetchBugDetails(long workItemId, String organization, String project, String pat, Long solutionId ) {
    	String apiUrl = String.format("https://dev.azure.com/%s/%s/_apis/wit/workitems/%d?api-version=6.0", organization, project, workItemId);
//        System.out.println(apiUrl);
        
        HttpClient client = HttpClient.newHttpClient();
        
        String authHeaderValue = "Basic " + Base64.getEncoder().encodeToString((":" + pat).getBytes());
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Authorization", authHeaderValue)
                .build();
        DefectMasterDTO defectMasterDTO = new DefectMasterDTO();

        
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                JSONObject workItemJson = new JSONObject(response.body());
//                System.out.println(workItemJson);
                JSONObject fields = workItemJson.getJSONObject("fields");
                // Extracting specific fields
                long bugId = workItemId;
                String title = fields.getString("System.Title");
                String state = fields.getString("System.State");
                String reason = fields.getString("System.Reason");
                String area = fields.getString("System.AreaPath");
                String iterationPath = fields.getString("System.IterationPath");
                JSONObject assignedTo = fields.optJSONObject("System.AssignedTo");
                String assignedToName = assignedTo != null ? assignedTo.getString("uniqueName") : "Unassigned";	                
                String commentWithHTML = fields.optString("System.History","");
    		    String comment = commentWithHTML.replaceAll("\\<.*?\\>", "");
                JSONObject CreatedBy = fields.optJSONObject("System.CreatedBy");
                String createdByName = CreatedBy != null ? CreatedBy.getString("uniqueName") : "Unassigned";	
                String createdDate = fields.optString("System.CreatedDate", "");
                JSONObject closedByName = fields.optJSONObject("Microsoft.VSTS.Common.ClosedBy");
                String closedBy= closedByName != null ? closedByName.getString("uniqueName") : "Unassigned";                
                String closedDate = fields.optString("Microsoft.VSTS.Common.ClosedDate");
                String reproStepsHTML = fields.optString("Microsoft.VSTS.TCM.ReproSteps", "");
    		    String reproSteps = reproStepsHTML.replaceAll("\\<.*?\\>", "");
                String severity = fields.optString("Microsoft.VSTS.Common.Severity", "");
                long storyPoints = fields.optLong("Microsoft.VSTS.Scheduling.StoryPoints",0);
                long remainingWork = fields.optLong("Microsoft.VSTS.Scheduling.RemainingWork", 0);
                long originalEstimate = fields.optLong("Microsoft.VSTS.Scheduling.OriginalEstimate", 0);              
             // Fetching parent ID and title from links
                String parentFeatureId = "";
                String parentFeatureTitle = "";
                // Print parent details
//                System.out.println("Parent Feature ID: " + parentFeatureId);
//                System.out.println("Parent Feature Title: " + parentFeatureTitle);
                String commentsWithHTML = fields.optString("System.History", "");
                List<String> comments = extractComments(commentsWithHTML);
//                System.out.println("List of comments:");
//                for (String c : comments) {
//                    System.out.println(c);
//                }
          
                defectMasterDTO.setBugId(bugId);
                defectMasterDTO.setTitle(title);
                defectMasterDTO.setReproSteps(reproSteps);
                defectMasterDTO.setState(state);
                defectMasterDTO.setSeverity(severity);
                defectMasterDTO.setReason(reason);
                defectMasterDTO.setAssignedTo(assignedToName);
                defectMasterDTO.setCreatedBy(createdByName);
                defectMasterDTO.setCreatedDate(createdDate);
                defectMasterDTO.setClosedDate(closedDate);
                defectMasterDTO.setClosedBy(closedBy);              
                defectMasterDTO.setIterationPath(iterationPath);
                defectMasterDTO.setStoryPoints(storyPoints);
                defectMasterDTO.setComment(comment);
                defectMasterDTO.setClosingComment(comment);
                defectMasterDTO.setEfforts(originalEstimate);
                defectMasterDTO.setArea(area);
                defectMasterDTO.setSolutionId(solutionId);


                // Print the extracted fields
//                System.out.println("BugId: " + bugId);
//                System.out.println("Title: " + title);
//                System.out.println("State: " + state);
//                System.out.println("Reason: " + reason);
//                System.out.println("Area: " + area);
//                System.out.println("Iteration Path: " + iterationPath);
//                System.out.println("Assigned To: " + assignedToName);
//                System.out.println("Comment: " + comment);
//                System.out.println("Created By: " + createdByName);
//                System.out.println("Created Date: " + createdDate);
//                System.out.println("Closed By: " + closedBy);
//                System.out.println("Closed Date: " + closedDate);
////                System.out.println("Closing Comment: " + closingComment);
//                System.out.println("Repro Steps: " + reproSteps);
//                System.out.println("Severity: " + severity);
//                System.out.println("Story Points: " + storyPoints);
//                if (remainingWork != -1 && originalEstimate != -1) {
//	                System.out.println("Efforts: " + originalEstimate);
//                } else {
//                    System.out.println("Effort fields not found or values not available.");
//                }
//                System.out.println("Parent Feature ID: " + parentFeatureId);
//                System.out.println("Parent Feature Title: " + parentFeatureTitle);
            } else {
                System.err.println("Error fetching work item. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return defectMasterDTO;
    }
    private static List<String> extractComments(String comment) {
        List<String> comments = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\[COMMENT\\](.*?)\\[/COMMENT\\]");
        Matcher matcher = pattern.matcher(comment);
        while (matcher.find()) {
            comments.add(matcher.group(1));
        }
        return comments;
    }

}
