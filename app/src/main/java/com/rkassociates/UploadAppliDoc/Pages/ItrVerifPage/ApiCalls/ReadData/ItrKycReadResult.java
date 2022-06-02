package com.rkassociates.UploadAppliDoc.Pages.ItrVerifPage.ApiCalls.ReadData;

import com.google.gson.annotations.SerializedName;

public class ItrKycReadResult {

    @SerializedName("itr_kyc_verification_table")
    private ItrKycVerificationTable itrKycVerificationTable;

    public ItrKycVerificationTable getItrKycVerificationTable() {
        return itrKycVerificationTable;
    }

    public void setItrKycVerificationTable(ItrKycVerificationTable itrKycVerificationTable) {
        this.itrKycVerificationTable = itrKycVerificationTable;
    }
}
