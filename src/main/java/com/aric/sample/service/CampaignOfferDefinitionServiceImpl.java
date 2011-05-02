/**
 * 
 */
package com.aric.sample.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aric.sample.domain.Campaign;
import com.aric.sample.domain.Offer;
import com.aric.sample.repository.CampaignRepository;
import com.aric.sample.repository.OfferRepository;

/**
 * @author Dursun KOC
 * 
 */
@Service()
public class CampaignOfferDefinitionServiceImpl implements
		CampaignOfferDefinitionService {
	Logger logger = Logger.getLogger(CampaignOfferDefinitionServiceImpl.class);
	@Autowired
	private CampaignRepository campaignRepository;

	@Autowired
	private OfferRepository offerRepository;

	/**
	 * @param campaignRepository
	 */
	public void setCampaignRepository(CampaignRepository campaignRepository) {
		this.campaignRepository = campaignRepository;
	}

	/**
	 * @param offerRepository
	 */
	public void setOfferRepository(OfferRepository offerRepository) {
		this.offerRepository = offerRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.service.CampaignOfferDefinitionService#getCampaignWithOffers
	 * (java.lang.Long)
	 */
	public Campaign getCampaignWithOffers(Long id) {
		Campaign campaign = this.campaignRepository.getCampaign(id);
		Hibernate.initialize(campaign.getOfferSet());
		return campaign;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.service.CampaignOfferDefinitionService#persistCampaign
	 * (com.aric.sample.domain.Campaign)
	 */
	public void persistCampaign(Campaign campaign) {
		campaignRepository.saveCampaign(campaign);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.service.CampaignOfferDefinitionService#persistOffer(com
	 * .aric.sample.domain.Offer)
	 */
	public void persistOffer(Offer offer) {
		offerRepository.saveOffer(offer);
	}

	/**
	 * @see com.aric.sample.service.CampaignOfferDefinitionService#persistCampaigns
	 *      (java.util.List)
	 */
	public void persistCampaigns(List<Campaign> campaigns) {
		for (Campaign campaign : campaigns) {
			try {
				logger.debug("Saving campaign "+campaign);
				campaignRepository.saveCampaign(campaign);
			} catch (Exception e) {
				System.out.println("Could not save campaign: " + campaign
						+ "\nReason:" + e.getMessage());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.aric.sample.service.CampaignOfferDefinitionService#persistOffers(
	 * java.util.List)
	 */
	public void persistOffers(List<Offer> offers) {
		// TODO Auto-generated method stub

	}

}
