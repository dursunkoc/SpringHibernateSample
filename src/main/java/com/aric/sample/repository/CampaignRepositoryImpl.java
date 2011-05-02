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
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.aric.sample.domain.Campaign;

/**
 * @author Dursun KOC
 * 
 */
@Repository
public class CampaignRepositoryImpl implements CampaignRepository {

	private HibernateTemplate ht;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.ht = new HibernateTemplate(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.CampaignRepository#getCampaign(java.lang.Long)
	 */
	public Campaign getCampaign(Long id) {
		return ht.load(Campaign.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.aric.sample.repository.CampaignRepository#getAllCampaigns()
	 */
	public List<Campaign> getAllCampaigns() {
		return ht.loadAll(Campaign.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.CampaignRepository#getActiveCampaigns(java
	 * .util.Date, java.util.Date)
	 */
	@SuppressWarnings("unchecked")
	public List<Campaign> getActiveCampaigns(Date startDate, Date endDate) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Campaign.class);
		Criterion criStartDate = Property.forName("startDate").eq(startDate);
		Criterion criEndDate = Property.forName("endDate").eq(startDate);
		criteria.add(Restrictions.and(criStartDate, criEndDate));
		List<Campaign> campaignList = ht.findByCriteria(criteria);
		return campaignList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.CampaignRepository#saveCampaign(com.aric.sample
	 * .domain.Campaign)
	 */
	public Long saveCampaign(Campaign campaign) {
		ht.saveOrUpdate(campaign);
		if(campaign.getName().equals("STUPID")){
			throw new RuntimeException("STUPID");
		}
		return campaign.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.CampaignRepository#deleteCampaign(com.aric
	 * .sample.domain.Campaign)
	 */
	public void deleteCampaign(Campaign campaign) {
		ht.delete(campaign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.repository.CampaignRepository#deleteCampaign(java.lang
	 * .Long)
	 */
	public void deleteCampaign(Long id) {
		Campaign campaign = ht.load(Campaign.class, id);
		if (campaign != null) {
			ht.delete(campaign);
		}
	}

}
