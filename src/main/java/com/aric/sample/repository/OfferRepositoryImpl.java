/**
 * 
 */
package com.aric.sample.repository;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.aric.sample.domain.Offer;

/**
 * @author Dursun KOC
 * 
 */
@Repository
public class OfferRepositoryImpl implements OfferRepository {

	private HibernateTemplate ht;

	/**
	 * @param sessionFactory
	 */
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		ht = new HibernateTemplate(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.sample.repository.OfferRepository#getOffer(java.lang.Long)
	 */
	public Offer getOffer(Long id) {
		return ht.load(Offer.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.sample.repository.OfferRepository#getAllOffers()
	 */
	public List<Offer> getAllOffers() {
		return ht.loadAll(Offer.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.OfferRepository#getOffersForCampaign(java.
	 * lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public List<Offer> getOffersForCampaign(Long campaignId) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Offer.class);
		Criterion criCampaignId = Property.forName("campaignId").eq(campaignId);
		criteria.add(criCampaignId);
		List<Offer> offerList = ht.findByCriteria(criteria);
		return offerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.OfferRepository#getActiveOffersForCampaign
	 * (java.lang.Long, java.util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<Offer> getActiveOffersForCampaign(Long campaignId,
			Date startDate, Date endDate) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Offer.class);
		Criterion criCampaignId = Property.forName("campaignId").eq(campaignId);
		Criterion criStartDate = Property.forName("startDate").eq(startDate);
		Criterion criEndDate = Property.forName("endDate").eq(startDate);
		criteria.add(criStartDate);
		criteria.add(criEndDate);
		criteria.add(criCampaignId);
		List<Offer> offerList = ht.findByCriteria(criteria);
		return offerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.OfferRepository#getActiveOffers(java.util.
	 * Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<Offer> getActiveOffers(Date startDate, Date endDate) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Offer.class);
		Criterion criStartDate = Property.forName("startDate").eq(startDate);
		Criterion criEndDate = Property.forName("endDate").eq(startDate);
		criteria.add(criStartDate);
		criteria.add(criEndDate);
		List<Offer> offerList = ht.findByCriteria(criteria);
		return offerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.OfferRepository#saveOffer(com.aric.sample.
	 * domain.Offer)
	 */
	public Long saveOffer(Offer offer) {
		ht.saveOrUpdate(offer);
		return offer.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.OfferRepository#deleteOffer(com.aric.sample
	 * .domain.Offer)
	 */
	public void deleteOffer(Offer offer) {
		ht.delete(offer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.OfferRepository#deleteOffer(java.lang.Long)
	 */
	public void deleteOffer(Long id) {
		Offer offer = ht.load(Offer.class, id);
		if (offer != null) {
			ht.delete(offer);
		}
	}

}
