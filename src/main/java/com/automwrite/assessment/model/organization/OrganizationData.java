package com.automwrite.assessment.model.organization;

import lombok.Data;
import java.util.List;

@Data
public class OrganizationData {
    private OrganizationInfo organizationInfo;
}

@Data
class OrganizationInfo {
    private OrganizationDetails organizationDetails;
    private Platforms platforms;
    private ServiceProposition serviceProposition;
    private Fees fees;
}

@Data
class OrganizationDetails {
    private String organizationName;
    private String websiteUrl;
    private String email;
    private String phoneNumber;
    private Address address;
}

@Data
class Address {
    private String street;
    private String city;
    private String postcode;
    private String country;
}

@Data
class Platforms {
    private List<Platform> items;
}

@Data
class Platform {
    private String name;
    private String description;
    private Fees fees;
    private List<String> features;
}

@Data
class Fees {
    private List<StructuredCharge> structuredCharge;
}

@Data
class StructuredCharge {
    private String startAmount;
    private String endAmount;
    private String fee;
}

@Data
class ServiceProposition {
    private String adviceType;
    private List<InvestmentPortfolio> investmentPortfolios;
}

@Data
class InvestmentPortfolio {
    private String name;
    private String description;
    private int riskLevel;
    private TargetAllocation targetAllocation;
    private AnnualManagementCharge annualManagementCharge;
    private long minimumInvestment;
}

@Data
class TargetAllocation {
    private int bonds;
    private int equities;
    private int cash;
    private Integer alternatives;
}

@Data
class AnnualManagementCharge {
    private String percentage;
}

@Data
class OrganizationFees {
    private Fee initialAdviceFee;
    private Fee ongoingAdviceFee;
    private Fee platformFee;
}

@Data
class Fee {
    private String percentage;
    private String description;
}
