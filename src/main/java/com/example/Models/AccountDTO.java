package com.example.Models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class AccountDTO implements Serializable {

    @Min(1)
    @Max(8)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    
    @NotNull
    private String accountName;
    
    @NotNull
    private String industry;
    
    @NotEmpty
    private Long revenue;
    
    @NotNull
    private String accountManager;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Long getRevenue() {
		return revenue;
	}

	public void setRevenue(Long revenue) {
		this.revenue = revenue;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountId, accountName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountDTO other = (AccountDTO) obj;
		return accountId == other.accountId && Objects.equals(accountName, other.accountName);
	}

	@Override
	public String toString() {
		return "AccountDTO [accountId=" + accountId + ", accountName=" + accountName + ", industry=" + industry
				+ ", revenue=" + revenue + ", accountManager=" + accountManager + "]";
	}

	public AccountDTO() {
		super();
	}
    
	

}