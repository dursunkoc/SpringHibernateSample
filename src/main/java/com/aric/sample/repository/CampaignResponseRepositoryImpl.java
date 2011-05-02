/**
 * 
 */
package com.aric.sample.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.aric.sample.domain.CampaignResponse;

/**
 * @author Dursun KOC
 *
 */
@Repository
public class CampaignResponseRepositoryImpl implements
		CampaignResponseRepository {

	private HibernateTemplate ht;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.ht = new HibernateTemplate(sessionFactory);
	}

	
	/* (non-Javadoc)
	 * @see com.aric.sample.repository.CampaignResponseRepository#writeCampaignResponse(java.lang.Long)
	 */
	public long writeCampaignResponse(Long campaignId) {
		CampaignResponse campaignResponse = getCampaignResponse(campaignId);
		if(campaignResponse!=null){
			campaignResponse.setCount(campaignResponse.getCount()+1);
		}else{
			campaignResponse = new CampaignResponse();
			campaignResponse.setCampaignId(campaignId);
			campaignResponse.setCount(1);
		}
		ht.save(campaignResponse);
		return campaignResponse.getId();
	}
	
	
	@SuppressWarnings("unchecked")
	private CampaignResponse getCampaignResponse(Long campaignId){
		DetachedCriteria criteria = DetachedCriteria.forClass(CampaignResponse.class);
		criteria.add(Property.forName("campaignId").eq(campaignId));
		List<CampaignResponse> campaignResponseList = ht.findByCriteria(criteria);
		if(campaignResponseList.size()<=0){
			return null;
		}else{
			return campaignResponseList.get(0);
		}
	}

}
