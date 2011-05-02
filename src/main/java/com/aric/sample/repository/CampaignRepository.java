/**
 * 
 */
package com.aric.sample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aric.sample.domain.Campaign;

/**
 * Repository for campaign Object
 * 
 * @author Dursun KOC
 */
public interface CampaignRepository {
	/**
	 * Gets campaign by id
	 * 
	 * @param id
	 * @return - the campaign
	 */
	@Transactional(readOnly = true)
	public Campaign getCampaign(Long id);

	/**
	 * Gets whole campaigns.
	 * 
	 * @return - campaign list
	 */
	@Transactional(readOnly = true)
	public List<Campaign> getAllCampaigns();

	/**
	 * Gets active campaign set with in the given date range, from startDate
	 * till the endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Campaign> getActiveCampaigns(Date startDate, Date endDate);

	/**
	 * Persists given Campaign object.
	 * 
	 * @param campaign
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public Long saveCampaign(Campaign campaign);

	/**
	 * Deletes given Campaign object from db.
	 * 
	 * @param campaign
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public void deleteCampaign(Campaign campaign);

	/**
	 * Deletes Campaign object from db for given id.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public void deleteCampaign(Long id);
}
