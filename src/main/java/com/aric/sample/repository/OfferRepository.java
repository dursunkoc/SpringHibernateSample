/**
 * 
 */
package com.aric.sample.repository;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aric.sample.domain.Offer;

/**
 * Repository for Offer Object
 * 
 * @author Dursun KOC
 */
public interface OfferRepository {
	/**
	 * Gets offer by id
	 * 
	 * @param id
	 * @return - offer
	 */
	@Transactional(readOnly = true)
	public Offer getOffer(Long id);

	/**
	 * Gets whole offers.
	 * 
	 * @return - offer list
	 */
	@Transactional(readOnly = true)
	public List<Offer> getAllOffers();

	/**
	 * Gets Offers of the specific campaign
	 * 
	 * @param campaignId
	 * @return - offer list
	 */
	@Transactional(readOnly = true)
	public List<Offer> getOffersForCampaign(Long campaignId);

	/**
	 * Gets active offers set for a specific campaign with in the given date
	 * range, from startDate till the endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Offer> getActiveOffersForCampaign(Long campaignId,
			Date startDate, Date endDate);

	/**
	 * Gets active offers set with in the given date range, from startDate till
	 * the endDate
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Offer> getActiveOffers(Date startDate, Date endDate);

	/**
	 * Persists given Offer object.
	 * 
	 * @param offer
	 * @return
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public Long saveOffer(Offer offer);

	/**
	 * Deletes given Offer object from db.
	 * 
	 * @param offer
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public void deleteOffer(Offer offer);

	/**
	 * Deletes Offer object from db for given id.
	 * 
	 * @param id
	 */
	@Transactional(readOnly = false, propagation = Propagation.NESTED)
	public void deleteOffer(Long id);
}
