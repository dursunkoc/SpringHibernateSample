/**
 * 
 */
package com.aric.sample.repository;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dursun KOC
 * 
 */
public interface CampaignResponseRepository {

	@Transactional(readOnly = false, isolation = Isolation.READ_UNCOMMITTED)
	public long writeCampaignResponse(Long campaignId);

}
