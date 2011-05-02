/**
 * 
 */
package com.aric.sample.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aric.sample.domain.Campaign;
import com.aric.sample.domain.Offer;

/**
 * @author Dursun KOC
 * 
 */
public interface CampaignOfferDefinitionService {
	/**
	 * Get campaign Object with the offerSet is initialized.
	 * @param id
	 * @return - campaign
	 */
	@Transactional(readOnly=true)
	public Campaign getCampaignWithOffers(Long id);
	
	/**
	 * @param campaign
	 */
	@Transactional(readOnly=false,propagation=Propagation.NESTED)
	public void persistCampaign(Campaign campaign);
	
	/**
	 * @param offer
	 */
	@Transactional(readOnly=false,propagation=Propagation.NESTED)
	public void persistOffer(Offer offer);
	
	/**
	 * @param campaigns
	 */
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void persistCampaigns(List<Campaign> campaigns);
	
	/**
	 * @param offers
	 */
	@Transactional(readOnly=false, propagation=Propagation.NESTED)
	public void persistOffers(List<Offer> offers);
}
